package sunday.mobility.gusers.data

import sunday.mobility.gusers.data.local.db.DbHelper
import sunday.mobility.gusers.data.remote.ApiHelper

interface DataManager : DbHelper, ApiHelper
