import com.qcloud.vod.model.VodUploadRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val coroutineScope = CoroutineScope(Dispatchers.IO)

fun main() = runBlocking {
    println("start update")
    val req = Application.vod.createVodRequest(
        VodRequest(
            media = "/Users/nolions/workspace/kotlin/testTencentVod/assets/a123_demoVideo.mp4",
            cover = "/Users/nolions/workspace/kotlin/testTencentVod/assets/a123.png",
            name = "test"
        )
    )

    val job = coroutineScope.launch {
        val resp = Application.vod.updateVideo(req)
//        withContext(Dispatchers.Main) {
        println("fileId:${resp.fileId}")
        println("mediaUrl:${resp.mediaUrl}")

//        }
    }

    job.join()
    println("end update")
}
