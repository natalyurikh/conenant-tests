package com.natalya.challenge.covenant.step.grunt;

import com.natalya.challenge.covenant.pageobject.DashBoardPage;
import java.util.List;
import java.util.Map;

public class GruntService {

    public List<Map<String, String>> getGrunts() {
        return new DashBoardPage().navigation().openGruntsViewPage().getGruntList();
    }
}
