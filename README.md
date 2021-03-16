# Android Parcel Bug
This repository aims to reproduce a bug with unmarshalling `Parcelable`s in Fragment 1.3.1.

This issue does not exist in Fragment 1.2.5.

### Testing
Build & run the app. Click on the "Test" button to reproduce the crash on Fragment 1.3.1.

To switch to Fragment 1.2.5, update `gradle.properties` and do a clean build:
```properties
app.useNewVersions=false
```

### Overview
- Start in `MainActivity`.
- When the button is clicked, it creates a "destination" intent and an "intermediate" intent.
- A `Parcelable` model is put into the "destination" intent extras.
- The "destination" intent itself is put into the "intermediate" intent extras.
- The "intermediate" intent is started, no issues here.
- The `IntermediateActivity` loads the `IntermediateFragment`, which get the "destination" intent from the extras.
- The "destination" intent is then started for result, which causes the crash.

### Stacktrace
```
com.github.mrbean355.android.parcelbug E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.github.mrbean355.android.parcelbug, PID: 21178
    android.os.BadParcelableException: ClassNotFoundException when unmarshalling: com.github.mrbean355.android.parcelbug.TestModel
        at android.os.Parcel.readParcelableCreator(Parcel.java:2839)
        at android.os.Parcel.readParcelable(Parcel.java:2765)
        at android.os.Parcel.readValue(Parcel.java:2668)
        at android.os.Parcel.readArrayMapInternal(Parcel.java:3037)
        at android.os.BaseBundle.initializeFromParcelLocked(BaseBundle.java:288)
        at android.os.BaseBundle.unparcel(BaseBundle.java:232)
        at android.os.BaseBundle.containsKey(BaseBundle.java:504)
        at android.content.Intent.hasExtra(Intent.java:7257)
        at androidx.activity.ComponentActivity$2.onLaunch(ComponentActivity.java:168)
        at androidx.activity.result.ActivityResultRegistry$3.launch(ActivityResultRegistry.java:224)
        at androidx.activity.result.ActivityResultLauncher.launch(ActivityResultLauncher.java:47)
        at androidx.fragment.app.FragmentManager.launchStartActivityForResult(FragmentManager.java:2996)
        at androidx.fragment.app.Fragment.startActivityForResult(Fragment.java:1424)
        at androidx.fragment.app.Fragment.startActivityForResult(Fragment.java:1398)
        at com.github.mrbean355.android.parcelbug.IntermediateFragment.onViewCreated(IntermediateFragment.kt:21)
        at androidx.fragment.app.Fragment.performViewCreated(Fragment.java:2976)
        at androidx.fragment.app.FragmentStateManager.createView(FragmentStateManager.java:546)
        at androidx.fragment.app.FragmentStateManager.moveToExpectedState(FragmentStateManager.java:282)
        at androidx.fragment.app.FragmentManager.executeOpsTogether(FragmentManager.java:2189)
        at androidx.fragment.app.FragmentManager.removeRedundantOperationsAndExecute(FragmentManager.java:2100)
        at androidx.fragment.app.FragmentManager.execPendingActions(FragmentManager.java:2002)
        at androidx.fragment.app.FragmentManager.dispatchStateChange(FragmentManager.java:3134)
        at androidx.fragment.app.FragmentManager.dispatchActivityCreated(FragmentManager.java:3068)
        at androidx.fragment.app.FragmentController.dispatchActivityCreated(FragmentController.java:251)
        at androidx.fragment.app.FragmentActivity.onStart(FragmentActivity.java:501)
        at androidx.appcompat.app.AppCompatActivity.onStart(AppCompatActivity.java:210)
        at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1391)
        at android.app.Activity.performStart(Activity.java:7157)
        at android.app.ActivityThread.handleStartActivity(ActivityThread.java:2937)
        at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:180)
        at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:165)
        at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:142)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:70)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:193)
        at android.app.ActivityThread.main(ActivityThread.java:6669)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
```
