import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val fabVisible = MutableLiveData<Boolean>()

    init {
        // Initially set FAB visibility to true
        fabVisible.value = true
    }
}