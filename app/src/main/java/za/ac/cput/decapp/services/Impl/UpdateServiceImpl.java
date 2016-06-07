package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Domain.Suspect;
import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.SuspectRepository;
import za.ac.cput.decapp.Repositories.Repository;
import za.ac.cput.decapp.services.UpdateInfoService;

/**
 * Created by User on 2016/05/04.
 */
// this service will be used to update the information of suspect with relevant information
    // that is verified
    // this is a bound service
public  abstract class UpdateServiceImpl extends IntentService implements UpdateInfoService{
    private static UpdateServiceImpl service = null;

    public static UpdateServiceImpl getInstance() {
        if (service == null)
            service = new UpdateServiceImpl();
        return service;
    }

    private UpdateServiceImpl() {
        super("UpdateServiceImpl");
        repository = new SuspectRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addSuspect(Context context, UpdateInfoService UpdateInfoService) {
        Intent intent = new Intent(context, UpdateServiceImpl.class);
        intent.setAction(addSuspect(context,UpdateInfoService,"");
        intent.putExtra(addInfo();, UpdateInfoService);
        context.startService(intent);
    }

    @Override
    public void resetSuspects(Context context) {
        Intent intent = new Intent(context, UpdateServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final UpdateInfoService UpdateInfoService = (UpdateInfoService) intent.getSerializableExtra(EXTRA_ADD);
                saveSuspect(UpdateInfoService);
            } else if (ACTION_RESET.equals(action)) {
                resetSuspectsRecords();
            }
        }
    }

    private void resetSuspectsRecords() {
        Repository.deleteAll();
    }

    private void saveSuspect(UpdateInfoService UpdateInfoService) {
        Suspect Suspect = new Suspect.Builder()
                .SuspectId(UpdateInfoService.getSuspectId())
                .firstname(UpdateInfoService.getFirstname())
                .lastName(UpdateInfoService.getLastName())
                .build();
        Suspect savedSuspect = repository.save(Suspect);

    }
}


