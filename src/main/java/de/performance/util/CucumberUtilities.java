package de.performance.util;

import io.cucumber.datatable.DataTable;

import java.util.List;

public class CucumberUtilities {

    public static List<List<String>> convertDataTable(DataTable dataTable) {
        return dataTable.asLists();
    }
}
