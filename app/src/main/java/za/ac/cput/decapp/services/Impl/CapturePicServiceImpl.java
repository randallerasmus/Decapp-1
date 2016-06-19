package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Conf.util.AppUtil;
import za.ac.cput.decapp.Domain.Suspect;
import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
import za.ac.cput.decapp.services.CapturePicService;
import 

/**
 * Created by User on 2016/05/04.
 */
// this service will be used mainly by detectives and the Crime intelligence branche to to alert the uniform branch about a
    // list of suspects that are wanted with the type of crime they are wanted for
public class CapturePicServiceImpl extends IntentService implements CapturePicService {

    private CapturePicServiceImpl service = null;

    public static CapturePicServiceImpl getInstance() {
        if (service == null)
            service = new CapturePicServiceImpl();
        return service;
    }


    public CapturePicServiceImpl()
    {
        super("CapturePicServiceImpl");
        repository = new SuspectRepositoryImpl(App.getAppContext());

    }

    @Override
    public void addSuspect(Context context, SuspectResourse SuspectResourse) {
        Intent intent = new Intent(context, CapturePicServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, SuspectResourse);
        context.startService(intent);
    }

    @Override
    public void resetSuspects(Context context) {
        Intent intent = new Intent(context, CapturePicServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final SuspectResource SuspectResource = (SuspectResource) intent.getSerializableExtra(EXTRA_ADD);
                saveSuspect(SuspectResourse);
            } else if (ACTION_RESET.equals(action)) {
                resetSuspectsRecords();
            }
        }
    }

    private void resetSuspectsRecords() {
        repository.deleteAll();
    }

    private void saveSuspect(SuspectResourse SuspectResourse) {
        Suspect Suspect = new Suspect.Builder()
                .SuspectId(SuspectResourse.getSuspectId())
                .SuspectImage(AppUtil.getImage(SuspectResourse.getSuspectImageUrl()))
                .firstname(SuspectResourse.getFirstname())
                .lastName(SuspectResourse.getLastName())
                .build();
        Suspect savedSuspect = Suspect.save(Suspect);

    }
}


