"""
Defines the blueprint for the Cluster
"""
from flask import Blueprint
from flask_restful import Api

from resources import ClusterResource

CLUSTER_BLUEPRINT = Blueprint("cluster", __name__)
Api(CLUSTER_BLUEPRINT).add_resource(
    ClusterResource, "/cluster"
)
