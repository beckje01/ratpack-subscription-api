package beckje01

import com.google.inject.Inject
import ratpack.exec.ExecControl
import ratpack.exec.Fulfiller
import ratpack.exec.Promise
import ratpack.launch.LaunchConfig

class SubscriptionService {

    private final LaunchConfig launchConfig

    @Inject
    SubscriptionService(LaunchConfig launchConfig) {
        this.launchConfig = launchConfig
    }

    Promise<Subscription> get(String id) {
        launchConfig.execController.control.blocking({
            //Some Blocking get
            return new Subscription(id: id)
        })
    }

    Promise<List<Subscription>> findByCid(String cid) {
        launchConfig.execController.control.blocking({
            return [new Subscription(id: UUID.randomUUID().toString()), new Subscription(id: UUID.randomUUID().toString())]
        })
    }

}
