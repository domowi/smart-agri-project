PROJECT = 'my-first-project-238015'
DATASET = 'waste'

TABLE_WEIGHT = 'weight'
TABLE_ROUTE = 'route'
TABLE_MERGE = 'merge'

BQ_TABLE_WEIGHT = '.'.join([DATASET, TABLE_WEIGHT])
BQ_TABLE_ROUTE = '.'.join([PROJECT, DATASET, TABLE_ROUTE])
BQ_TABLE_MERGE = '.'.join([DATASET, TABLE_MERGE])

BUCKET = 'austin_waste'
FOLDER_DAT = 'data'
FOLDER_RES = 'results'

SOURCE_OBJECT = FOLDER_DAT + '/' +'route.csv'
DESTINATION_OBJECT = FOLDER_RES + '/' + 'merge.csv'

BUCKET_URI = 'gs://' + BUCKET + '/' 
DESTINATION_URI = BUCKET_URI + DESTINATION_OBJECT

TYPE = 'DEAD ANIMAL'