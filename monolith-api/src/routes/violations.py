"""
Defines the blueprint for the Violation Listing
"""
from flask import Blueprint
from flask_restful import Api

from resources import ViolationsResource

VIOLATIONS_BLUEPRINT = Blueprint("violations", __name__)
Api(VIOLATIONS_BLUEPRINT).add_resource(
    ViolationsResource, "/violations"
)
