package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.BLJStore;

import java.util.List;

public class JFTOBoringButBigLiftStore extends BLJStore {
    public void setupDefaults() {
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift ftoLift = (JFTOLift) model;
            JFTOBoringButBigLift bbbLift = (JFTOBoringButBigLift) this.create();
            bbbLift.mainLift = ftoLift;
            bbbLift.boringLift = ftoLift;
        }
    }

    public void adjustToMainLifts() {
        this.addMissingLifts();
        this.removeUnnecessaryLifts();
    }

    private void removeUnnecessaryLifts() {
        for (int i = 0; i < this.count(); i++) {
            JFTOBoringButBigLift bbbLift = (JFTOBoringButBigLift) this.atIndex(i);
            if (JFTOLiftStore.instance().find("uuid", bbbLift.mainLift.uuid) == null) {
                this.remove(bbbLift);
                i--;
            }

            if (JFTOLiftStore.instance().find("uuid", bbbLift.boringLift.uuid) == null) {
                bbbLift.boringLift = (JFTOLift) JFTOLiftStore.instance().first();
            }
        }
    }

    private void addMissingLifts() {
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift jftoLift = (JFTOLift) model;
            if (this.find("mainLift", jftoLift) == null) {
                JFTOBoringButBigLift bbbLift = (JFTOBoringButBigLift) this.create();
                bbbLift.boringLift = jftoLift;
                bbbLift.mainLift = jftoLift;
            }
        }
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOBoringButBigLift.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JFTOLift.class);
    }

    public static JFTOBoringButBigLiftStore instance() {
        return (JFTOBoringButBigLiftStore) BLJStore.instance(JFTOBoringButBigLiftStore.class);
    }
}
