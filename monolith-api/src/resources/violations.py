"""
Define the REST verbs relative to the violation listing
"""

from flasgger import swag_from
from flask.json import jsonify
from flask_restful import Resource
from flask_restful.reqparse import Argument

from repositories import ViolationRepository
from util import parse_params


class ViolationsResource(Resource):
    """ Verbs relative to the violation listing """

    @staticmethod
    @parse_params(
        Argument("cctv_id", location="args", required=True, help="The unique ID of CCTV")
    )
    @swag_from("../swagger/violations/GET.yml")
    def getAllViolationByCctvId(cctv_id):
        """ Return violations on a cctv """
        violations = ViolationRepository.get(cctv_id = cctv_id)
        return jsonify({violations.json})