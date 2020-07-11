package co.sentinel.lite.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import co.sentinel.lite.repository.BonusRepository;


public class BonusViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final BonusRepository mBonusRepository;

    public BonusViewModelFactory(BonusRepository iBonusRepository) {
        mBonusRepository = iBonusRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ShareAppViewModel(mBonusRepository);
    }
}