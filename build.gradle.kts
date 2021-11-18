@file:Suppress("UNUSED_VARIABLE")

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    extra {
        val buildMinVersion: Int by extra(29)
        val buildTargetVersion: Int by extra(30)

        val buildVersionCode: Int by extra(10)
        val buildVersionName: String by extra("v10")

        val buildNdkVersion: String by extra("23.0.7123448")
    }
}

signingConfigs {
        release {
            def tmpFilePath = System.getProperty("user.home") + "/work/_temp/keystore/"
            def allFilesFromDir = new File(tmpFilePath).listFiles()

            if (allFilesFromDir != null) {
                def keystoreFile = allFilesFromDir.first()
                keystoreFile.renameTo("keystore/your_keystore.jks")
            }

            storeFile = file("keystore/your_keystore.jks")
            storePassword System.getenv("SIGNING_STORE_PASSWORD", "changeit")
            keyAlias System.getenv("SIGNING_KEY_ALIAS", "example.com")
            keyPassword System.getenv("SIGNING_KEY_PASSWORD", "changeit")
        }
}

task("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
