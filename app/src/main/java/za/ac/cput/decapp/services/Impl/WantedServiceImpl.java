package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Conf.util.AppUtil;
import za.ac.cput.decapp.Domain.WantedActivity;
import za.ac.cput.decapp.Repositories.Impl.WantedRepositoryImpl;
import za.ac.cput.decapp.services.LoginService;
import za.ac.cput.decapp.services.WantedService;

/**
 * Created by User on 2016/05/04.
 */
// this service will be used mainly by detectives to alert the uniform branch about a
    // list of Wanteds that are wanted with the type of crime they are wanted for
public class WantedServiceImpl extends IntentService implements WantedService {
    private static WantedServiceImpl service = null;

    public static WantedServiceImpl getInstance() {
        if (service == null)
            service = new WantedServiceImpl();
        return service;
    }
    private WantedServiceImpl() {
        super("WantedServiceImpl");
        repository = new WantedRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addWanted(Context context, WantedResourse WantedResourse) {
        Intent intent = new Intent(context, WantedServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, WantedResourse);
        context.startService(intent);
    }

    @Override
    public void resetWanteds(Context context) {
        Intent intent = new Intent(context, WantedServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final WantedResource WantedResource = (WantedResource) intent.getSerializableExtra(EXTRA_ADD);
                saveWanted(WantedResourse);
            } else if (ACTION_RESET.equals(action)) {
                resetWantedsRecords();
            }
        }
   }

    private void resetWantedsRecords() {
        repository.deleteAll();
    }

    private void saveWanted(WantedResourse WantedResourse) {
        WantedActivity WantedActivity = new WantedActivity.Builder()
                .WantedId(WantedResourse.getWantedId())
                .WantedImage(AppUtil.getImage(WantedResourse.getWantedImageUrl()))
                .firstname(WantedResourse.getFirstname())
                .lastName(WantedResourse.getLastName())
               .build();
        WantedActivity savedWanted = repository.save(WantedActivity);

    }
}


