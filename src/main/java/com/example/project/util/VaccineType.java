package com.example.project.util;

import java.util.List;
import java.util.Map;

public final class VaccineType {
    private VaccineType() {}

    public static final long PFIZER_BIONTECH = 1;
    public static final long MODERNA = 2;
    public static final long JOHNSONJOHNSONS_JANSSEN = 3;
    public static final long NOVAVAX = 4;

    public static final Map<Long, List<String>> VALUES = Map.of(
            PFIZER_BIONTECH, List.of( "The Pfizer vaccine for the novel coronavirus, SARS-CoV-2, appears to be effective in clinical trials and real-world situations. Current evidence shows it is safe for most adults.\n" +
                    "\n" +
                    "Common side effects include chills, fever, tiredness, headache, and pain or swelling at the injection site. This should only last a few days.\n" +
                    "\n" +
                    "People can discuss different vaccines and their safety with a healthcare professional. People still need to wear masks, practice physical distancing, and follow hygiene guidelines to prevent transmission until advised otherwise."),
            MODERNA, List.of( "Clinical trials and real world data suggest that the Moderna COVID-19 vaccine provides significant protection from the disease.\n" +
                    "\n" +
                    "The Moderna vaccine appears to be safe for most people, though minor side effects are common. Serious reactions, however, are rare.\n" +
                    "\n" +
                    "COVID-19 vaccines such as the Moderna vaccine are important tools in ending the pandemic.\n" +
                    "\n" +
                    "However, until the pandemic ends, people should continue to wear face masks, practice hand hygiene, and physically distance themselves from others, even after they receive their vaccination."),
            JOHNSONJOHNSONS_JANSSEN, List.of("Who can get the booster: In most situations, everyone ages 18 or older should get a Pfizer-BioNTech or Moderna vaccine booster dose at least 2 months after a single J&J primary shot (a J&J booster can be considered in some situations).\n"+"\n"+" In addition, all adults who completed a J&J primary vaccine and booster may receive a second booster from either Pfizer or Moderna.\n"+"\n"+"Possible side effects: Pain, redness, swelling in the arm where the shot was administered; tiredness, headache, muscle pain, chills, fever, nausea throughout the rest of the body. If any of these side effects occur, they should go away in a few days." ),
            NOVAVAX, List.of(" Who it may be recommended for: Adults 18 and older. The company is studying the vaccine in children and teenagers ages 12 to 17.\n" +
                    "\n" +
                    "Dosage: 2 doses, 21 days apart\n" +
                    "\n" +
                    "Possible side effects: Injection site tenderness, fatigue, headache, muscle pain. There were rare cases of myocarditis and pericarditis (six cases in 40,000 participants) in the clinical trial. ")
    );
}
