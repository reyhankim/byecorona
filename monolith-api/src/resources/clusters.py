"""
Define the REST verbs relative to the CCTV Cluster listing
"""

from flasgger import swag_from
from flask.json import jsonify
from flask_restful import Resource
from flask_restful.reqparse import Argument

from repositories import ClusterRepository, CctvRepository
from util import parse_params


class ClustersResource(Resource):
    """ Verbs relative to the CCTV cluster listing """

    @staticmethod
    @parse_params(
        Argument("longitude", location="args", required=True, help="The reference longitude to search for clusters"),
        Argument("latitude", location="args", required=True, help="The reference latitude to search for clusters"),
        Argument("radius", location="args", required=True, help="Radius from reference point to search for cluster")
    )
    @swag_from("../swagger/clusters/GET.yml")
    def get(longitude, latitude, radius):
        """ Return CCTV clusters from a reference point within a defined radius """
        clusters = ClusterRepository.getClustersByLocation(reference_long = longitude, reference_lat = latitude, radius = radius)
        cluster_list = []
        for cluster in clusters:
            cctv_list = CctvRepository.getAllCctvByClusterId(cluster_id = cluster.cluster_id)
            cluster_details = {
                "cluster_info": cluster,
                "cctv_list": cctv_list
            }
            cluster_list.append(cluster_details)
        return jsonify({cluster_list.json})