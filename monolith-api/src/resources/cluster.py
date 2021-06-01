"""
Define the REST verbs relative to the CCTV Cluster details
"""

from flasgger import swag_from
from flask.json import jsonify
from flask_restful import Resource
from flask_restful.reqparse import Argument

from repositories import ClusterRepository
from util import parse_params


class ClusterResource(Resource):
    """ Verbs relative to the cctv Cluster details"""

    @staticmethod
    @parse_params(
        Argument("cluster_id", location="args", required=True, help="Unique ID of a cluster")
    )
    @swag_from("../swagger/cluster/GET.yml")
    def getClusterById(cluster_id):
        """ Return cluster details of a cluster with specified unique ID """
        cluster = ClusterRepository.getClusterById(cluster_id = cluster_id)
        return jsonify({cluster.json})
