"""
Defines the blueprint for the CCTV
"""
from flask import Blueprint
from flask_restful import Api

from resources import CctvResource

CCTV_BLUEPRINT = Blueprint("cctv", __name__)
Api(CCTV_BLUEPRINT).add_resource(
    CctvResource, "/cctv"
)
