import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tw.nolions.testTencentVod.BuildConfig

val coroutineScope = CoroutineScope(Dispatchers.IO)

fun main() = runBlocking {
    tencentConfig()

    println("start update")

    updateVideo(
        "/Users/nolions/workspace/kotlin/testTencentVod/assets/a123_demoVideo.mp4",
        "/Users/nolions/workspace/kotlin/testTencentVod/assets/a123.png",
        "1234"
    )

//    deleteVideo("8602268011343238840")

    println("end update")
}

private suspend fun updateVideo(mediaPath: String, coverPath: String, name: String) {
    val req = Tencent.manager.createVodRequest(
        VodRequest(
            media = mediaPath,
            cover = coverPath,
            name = name
        )
    )

    val job = coroutineScope.launch {
        val resp = Tencent.manager.updateVideo(req)
//        withContext(Dispatchers.Main) {
        println("fileId:${resp.fileId}")
        println("mediaUrl:${resp.mediaUrl}")
//        }
    }

    job.join()
}

private suspend fun deleteVideo(fileId: String) {
    val job = coroutineScope.launch {
        val resp = Tencent.manager.deleteVideo(fileId)
        resp?.let {
            println("fileId:${it.requestId}")
        }
    }
    job.join()
}

private fun tencentConfig() {
    Tencent.SecretID = BuildConfig.VOD_SECRET_ID
    Tencent.SecretKey = BuildConfig.VOD_SECRET_KEY
    Tencent.VodProcedure = BuildConfig.VOD_PROCEDURE
    Tencent.VodRegion = BuildConfig.VOD_REGION
    Tencent.VodSubAppId = BuildConfig.VOD_SUB_APP_ID

    Tencent.init()
}
