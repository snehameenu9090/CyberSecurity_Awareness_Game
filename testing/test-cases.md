# Test Cases

**Status note:** These are proposed manual checks derived from the current implementation. No execution results are implied; each remains **Not tested yet** until performed and recorded.

| ID | Test Case | Expected Result | Actual Result | Pass/Fail |
|---|---|---|---|---|
| TC-01 | Build with Maven and Java 17 | Compilation and packaging complete without errors | Not tested yet | Not tested yet |
| TC-02 | Launch through the Windows runner | Main Swing application opens | Not tested yet | Not tested yet |
| TC-03 | Start a player using a blank name | Session is not started | Not tested yet | Not tested yet |
| TC-04 | Start a new player with a non-blank name | A player session is opened | Not tested yet | Not tested yet |
| TC-05 | Reopen an existing player with different letter casing | Existing normalized player record is reused | Not tested yet | Not tested yet |
| TC-06 | Change the selected study language | Selected language is reflected and persisted when a player is active | Not tested yet | Not tested yet |
| TC-07 | Award valid points with SQLite enabled | Player's non-negative total is updated and stored | Not tested yet | Not tested yet |
| TC-08 | Attempt to award negative points | The application rejects the invalid point value | Not tested yet | Not tested yet |
| TC-09 | Open each awareness lesson | The topic's guidance, example, and checklist are visible | Not tested yet | Not tested yet |
| TC-10 | Complete quiz and game rounds | Results and educational feedback are displayed | Not tested yet | Not tested yet |
| TC-11 | Run when SQLite is unavailable | Current-session player state uses the in-memory fallback | Not tested yet | Not tested yet |
| TC-12 | Review internal documentation links | Each relative link targets an existing repository file | Not tested yet | Not tested yet |
