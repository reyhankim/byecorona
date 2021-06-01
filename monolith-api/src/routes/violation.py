"""
Defines the blueprint for the Violation Details
"""
from flask import Blueprint
from flask_restful import Api

from resources import ViolationResource

VIOLATION_BLUEPRINT = Blueprint("violation", __name__)
Api(VIOLATION_BLUEPRINT).add_resource(
    ViolationResource, "/violation"
)
