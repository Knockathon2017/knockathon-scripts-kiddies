/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rsamadhan;

public class Domains {

        private static final int DRAWABLE_ARR[] = {R.drawable.agriculture_thumb_1,
                R.drawable.ah_icon_thumb,
                R.drawable.art_and_culture,
                R.drawable.biotechnology_thumb_1,
                R.drawable.census_and_surveys,
                R.drawable.commerce,
                R.drawable.defence,
                R.drawable.economy_thumb_1,
                R.drawable.education,
                R.drawable.environment_and_forest_thumb_1,
                R.drawable.finance,
                R.drawable.food,
                R.drawable.foreign_affairs,
                R.drawable.governance_administration_thumb_1,
                R.drawable.health_family_welfare_thumb_1,
                R.drawable.home_affairs_and_enforcement,
                R.drawable.housing,
                R.drawable.industries,
                R.drawable.information_and_broadcasting,
                R.drawable.information_communications_thumb_1,
                R.drawable.infrastructure,
                R.drawable.labour_employment_thumb_2,
                R.drawable.mining,
                R.drawable.parliament_of_india,
                R.drawable.power_and_energy,
                R.drawable.rural,
                R.drawable.science_technology,
                R.drawable.social_development,
                R.drawable.statistics,
                R.drawable.transport,
                R.drawable.travel_and_tourism,
                R.drawable.urban,
                R.drawable.water_and_sanitation,
                R.drawable.water_resource_thumb_1};
        
        
    public static int getDomainImage(int domainInd) {
      return DRAWABLE_ARR[domainInd];
    }
}
