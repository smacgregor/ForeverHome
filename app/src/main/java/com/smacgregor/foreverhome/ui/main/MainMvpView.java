package com.smacgregor.foreverhome.ui.main;

import java.util.List;

import com.smacgregor.foreverhome.data.model.Ribot;
import com.smacgregor.foreverhome.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
