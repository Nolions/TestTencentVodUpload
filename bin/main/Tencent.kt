import com.qcloud.vod.VodUploadClient
import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.vod.v20180717.VodClient
import tw.nolions.testTencentVod.BuildConfig

object Tencent {
    var SecretID: String = ""
    var SecretKey: String = ""
    var VodProcedure: String = ""
    var VodSubAppId: Long = 0
    var VodRegion: String = "ap-shanghai"

    lateinit var cred: Credential

    lateinit var vodClient: VodClient

    lateinit var vodUploadClient: VodUploadClient

    val manager = VideoUpdate()

    fun initUploadClient() {
        vodUploadClient = VodUploadClient(SecretID, SecretKey)
    }

    fun initCredential() {
        cred = Credential(SecretID, SecretKey)
    }

    fun initVodClient() {
        vodClient = VodClient(cred, BuildConfig.VOD_REGION)
    }

    fun init() {
        initCredential()
        initVodClient()
        initUploadClient()
    }
}