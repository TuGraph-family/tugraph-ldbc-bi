import jaydebeapi
import json


class driver(object):

    def __init__(self, lib_path, endpoint):
        jar_path = lib_path + "/tugraph-analysis-client-0.9.jar"
        self.connection = jaydebeapi.connect("com.antfin.gryphon.jdbc.GryphonDriver",
                                             "jdbc:TuGraph://{}/".format(endpoint),
                                             {},
                                             jar_path)

    @classmethod
    def instance(self, lib_path, endpoint):
        return driver(lib_path, endpoint)


    def query(self, query: str, parameter: dict):
        cursor = self.connection.cursor()
        para =  [] if parameter ==  None else [json.dumps(parameter)]
        cursor.execute(query, para)
        row = cursor.fetchone()
        cursor.close()
        return str(row[0])

