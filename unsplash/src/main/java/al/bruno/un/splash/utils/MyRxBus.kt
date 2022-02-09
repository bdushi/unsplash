package al.bruno.un.splash.utils

import al.bruno.un.splash.model.api.Search
import io.reactivex.rxjava3.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

class MyRxBus @Inject constructor() {
    private val subject = PublishSubject.create<Search>()

    fun setRxBus(t: Search) {
        try {
            subject.onNext(t)
        } catch (ex : Exception) {
            subject.onError(ex)
        }
    }

    fun getRxBus(): PublishSubject<Search> = subject
}