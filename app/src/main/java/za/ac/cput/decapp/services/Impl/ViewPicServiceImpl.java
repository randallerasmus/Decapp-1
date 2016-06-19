package za.ac.cput.decapp.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.Transfer;
import za.ac.cput.decapp.Repositories.Impl.TransferRepositoryImpl;
import za.ac.cput.decapp.services.LoginService;
import za.ac.cput.decapp.services.ViewPicService;

/**
 * Created by User on 2016/05/04.
 */
// this service will be used to present to victims of crime on the crime scene
    // a list of possible suspects that are related to the type of crime
public class ViewPicServiceImpl extends IntentService implements ViewPicService {

    private static TransferServiceImpl service = null;

    public static TransferServiceImpl getInstance() {
        if (service == null)
            service = new TransferServiceImpl();
        return service;
    }
    public ViewPicServiceImpl()
    {

    }

    private TransferServiceImpl() {
        super("TransferServiceImpl");
        repository = new TransferRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addTransfer(Context context, TransferResourse transferResourse) {
        Intent intent = new Intent(context, TransferServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, TransferResourse);
        context.startService(intent);
    }

    @Override
    public void resetTransfers(Context context) {
        Intent intent = new Intent(context, TransferServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final TransferResource TransferResource = (TransferResource) intent.getSerializableExtra(EXTRA_ADD);
                saveTransfer(TransferResourse);
            } else if (ACTION_RESET.equals(action)) {
                resetTransfersRecords();
            }
        }
    }

    private void resetTransfersRecords() {
        repository.deleteAll();
    }

    private void saveTransfer(TransferResourse TransferResourse) {
        Transfer Transfer = new Transfer.Builder()
                .TransferId(TransferResourse.getTransferId())
                .TransferImage(AppUtil.getImage(TransferResourse.getTransferImageUrl()))
                .firstname(TransferResourse.getFirstname())
                .lastName(TransferResourse.getLastName())
                .build();
        Transfer savedTransfer = repository.save(Transfer);

    }
}


