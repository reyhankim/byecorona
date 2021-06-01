"""
Defines the blueprint for the Cluster Listing
"""
from flask import Blueprint
from flask_restful import Api

from resources import ClustersResource

CLUSTERS_BLUEPRINT = Blueprint("clusters", __name__)
Api(CLUSTERS_BLUEPRINT).add_resource(
    ClustersResource, "/clusters"
)
