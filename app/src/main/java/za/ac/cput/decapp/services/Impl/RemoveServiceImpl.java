//package za.ac.cput.decapp.services.Impl;
//
//import android.app.IntentService;
//import android.content.Context;
//import android.content.Intent;
//
//import za.ac.cput.decapp.Domain.Suspect;
//import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
//
///**
// * Created by User on 2016/05/04.
// */
//// this services is used to remove unverified information of alleged suspects
//    // this is a bound service
//public class RemoveServiceImpl extends IntentService implements LoginService {
//
//    private static SuspectServiceImpl service = null;
//
//    public static SuspectServiceImpl getInstance() {
//        if (service == null)
//            service = new SuspectServiceImpl();
//        return service;
//    }
//    public RegisterServiceImpl()
//    {
//
//    }
//
//    private SuspectServiceImpl() {
//        super("SuspectServiceImpl");
//        repository = new SuspectRepositoryImpl(App.getAppContext());
//    }
//
//    @Override
//    public void addSuspect(Context context, SuspectResourse SuspectResourse) {
//        Intent intent = new Intent(context, SuspectServiceImpl.class);
//        intent.setAction(ACTION_ADD);
//        intent.putExtra(EXTRA_ADD, SuspectResourse);
//        context.startService(intent);
//    }
//
//    @Override
//    public void resetSuspects(Context context) {
//        Intent intent = new Intent(context, SuspectServiceImpl.class);
//        intent.setAction(ACTION_RESET);
//        context.startService(intent);
//
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        if (intent != null) {
//            final String action = intent.getAction();
//            if (ACTION_ADD.equals(action)) {
//                final SuspectResource SuspectResource = (SuspectResource) intent.getSerializableExtra(EXTRA_ADD);
//                saveSuspect(SuspectResourse);
//            } else if (ACTION_RESET.equals(action)) {
//                resetSuspectsRecords();
//            }
//        }
//    }
//
//    private void resetSuspectsRecords() {
//        repository.deleteAll();
//    }
//
//    private void saveSuspect(SuspectResourse SuspectResourse) {
//        Suspect Suspect = new Suspect.Builder()
//                .SuspectId(SuspectResourse.getSuspectId())
//                .SuspectImage(AppUtil.getImage(SuspectResourse.getSuspectImageUrl()))
//                .firstname(SuspectResourse.getFirstname())
//                .lastName(SuspectResourse.getLastName())
//
//                .build();
//        Suspect savedSuspect = repository.save(Suspect);
//
//    }
//}
//
//
