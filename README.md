This is an example of defining a service with AIDL.
This service provides two methods, getPID and genRandomString, which are both able to be called locally (in the same proccess) or remotely (in another proccess).

The first app (aidlserviceapp) is an example of how to use functions of the AIDL service locally.
On the other hand, the other app (app) is an example of how to use functions of the AIDL remotely via binder IPC.

The implementation is based on [the official tutorial from Android](https://developer.android.com/develop/background-work/services/bound-services), supplemented by [this detailed step-by-step article](https://blog.csdn.net/u011288271/article/details/111173368).

In order to learn about binder IPC, I strongly recommend reading the official document mentioned above, including both the "Bound Services" and "About AIDL" sections.
After reading the official docs and go through all the commits in this repo, one should be able to understand any binder services' code and also implement a new one.

If would you like to know the internal mechanisms on how binder service enables inter process function calls, I highly recommend reading the related chapter in [this fantastic book](https://play.google.com/store/books/details/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3Android%E5%86%85%E6%A0%B8%E8%AE%BE%E8%AE%A1%E6%80%9D%E6%83%B3_%E4%B8%8A%E4%B8%8B%E5%86%8C?id=1VNEEAAAQBAJ&hl=zh_TW&gl=US&pli=1) if you can read in Mandrain.
