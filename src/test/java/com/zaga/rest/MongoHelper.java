package com.zaga.rest;

import static org.awaitility.Awaitility.await;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.MongoCmdOptions;
import de.flapdoodle.embed.mongo.config.MongoImportConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.mongo.distribution.Version;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class MongoHelper {
    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    private final Net net;
    private final IFeatureAwareVersion version = Version.Main.V6_0;

    public MongoHelper() {
        net = new Net(27017, false);
    }

    void startDB() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongoCmdOptions cmdOptions = MongoCmdOptions.builder().useNoJournal(false).build();
        MongodConfig mongodConfig = MongodConfig.builder()
                .stopTimeoutInMillis(120000L)
                .net(net)
                .cmdOptions(cmdOptions)
                .version(version)
                .build();
        mongodExe = starter.prepare(mongodConfig);
        mongod = mongodExe.start();
    }

    void loadCollection(String dbName, String collection, String fixture) throws IOException {
        String fixturePath = Thread.currentThread().getContextClassLoader().getResource(fixture).getFile();
        File jsonFile = new File(fixturePath);

        MongoImportConfig mongoImportConfig = MongoImportConfig.builder()
            .version(version)
            .net(net)
            .databaseName(dbName)
            .collectionName(collection)
            .isUpsertDocuments(true)
            .isDropCollection(true)
            .isJsonArray(true)
            .importFile(jsonFile.getAbsolutePath())
            .build();

        MongoImportExecutable exec = MongoImportStarter
            .getDefaultInstance()
            .prepare(mongoImportConfig);

        MongoImportProcess mongoImportProcess = exec.start();
        mongoImportProcess.stop();
    }

    void stopDB() {

        try {
            mongod.stop();
        } catch (IllegalStateException e) {
            if (mongod != null) {
                await().atMost(1, TimeUnit.MINUTES).until(() -> !mongod.isProcessRunning());
            } else {
                throw new IllegalStateException(e);
            }
        }

        if (mongodExe != null) {
            try {
                mongodExe.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }
}
