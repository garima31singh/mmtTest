package org.mmt.Script;

import org.mmt.Base.Base;
import org.mmt.Pages.SearchFlights;
import org.testng.annotations.Test;

public class PerformSearch extends Base {
    @Test
    public void SearchFlightDetails() throws InterruptedException {
        SearchFlights ref= new SearchFlights(driver,pr);
        ref.performSearch();
    }
}
