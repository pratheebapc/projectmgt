package com.zaga.rest;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.MongoCmdOptions;
import de.flapdoodle.embed.mongo.config.MongoImportConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.mongo.distribution.Version;
import java.io.File;
import java.io.IOException;

public class MongoHelper {
    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    private Net net;
    private IFeatureAwareVersion version = Version.Main.V6_0;

    public MongoHelper() {
        net = new Net(27017, false);
    }

    void startDB() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongoCmdOptions cmdOptions = MongoCmdOptions.builder().useNoJournal(false).build();
        MongodConfig mongodConfig = MongodConfig.builder()
                .net(net)
                .cmdOptions(cmdOptions)
                .version(Version.Main.V6_0)
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
        mongod.stop();
        mongodExe.stop();
    }
}
