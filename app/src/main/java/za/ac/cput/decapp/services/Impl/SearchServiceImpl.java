package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Conf.util.AppUtil;
import za.ac.cput.decapp.Domain.Suspect;
import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
import za.ac.cput.decapp.services.LoginService;
import za.ac.cput.decapp.services.SearchService;

/**
 * Created by User on 2016/05/04.
 */
// this service is used to search a specific suspect to a police precinct
    // this is a bound service
public class SearchServiceImpl extends IntentService implements SearchService {
    private static SearchServiceImpl service = null;

    public static SearchServiceImpl getInstance() {
        if (service == null)
            service = new SearchServiceImpl();
        return service;
    }
    public SearchServiceImpl()
    {

    }

    private SearchServiceImpl() {
        super("SearchServiceImpl");
        repository = new SuspectRepositoryImpl(App.getAppContext());
    }

    @Override
    public void StartSearch(Context context, SearchService searchService) {
        Intent intent = new Intent(context, SearchServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, SuspectResourse);
        context.startService(intent);
    }

    @Override
    public void resetSearch(Context context) {
        Intent intent = new Intent(context, SearchServiceImpl.class);
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


