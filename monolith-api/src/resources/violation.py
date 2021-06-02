"""
Define the REST verbs relative to the violation details
"""

from flasgger import swag_from
from flask.json import jsonify
from flask_restful import Resource
from flask_restful.reqparse import Argument

from repositories import ViolationRepository
from util import parse_params


class ViolationResource(Resource):
    """ Verbs relative to the violation details """

    @staticmethod
    @parse_params(
        Argument("violation_id", location="args", required=True, help="The unique ID of Violation")
    )
    @swag_from("../swagger/violation/GET.yml")
    def get(violation_id):
        """ Return a violation by its id """
        violation = ViolationRepository.get(violation_id = violation_id)
        return jsonify({violation.json})


    @staticmethod
    @parse_params(
        Argument("violation_type", location="json", required=True, help="Type of the violation."),
        Argument("cctv_id", location="json", required=True, help="ID of CCTV generation the violation"),
        Argument("thumbnail_path", location="json", required=True, help="Path of the uploaded violation proof")
    )
    @swag_from("../swagger/violation/POST.yml")
    def post(violation_type, cctv_id, thumbnail_path):
        """ Create a violation based on generated information from a CCTV """
        violation = ViolationRepository.create(
            violation_type = violation_type, cctv_id = cctv_id, thumbnail_path = thumbnail_path
        )
        return jsonify({violation.json})


    @staticmethod
    @parse_params(
        Argument("violation_id", location="json", required=True, help="Violation ID on which the action info is updated"),
        Argument("action_type", location="json", required=True, help="Type of action done towards a violation"),
        Argument("action_remark", location="json", required=True, help="Officer's remark on action done")
    )
    @swag_from("../swagger/violation/PUT.yml")
    def put(violation_id, action_type, action_remark):
        """ Update a violation action details """
        repository = ViolationRepository()
        violation = repository.update(violation_id = violation_id, action_type = action_type, action_remark = action_remark)
        return jsonify({violation.json})


    @staticmethod
    @parse_params(
        Argument("violation_id", location="args", required=True, help="Violation ID to be flagged as false positive")
    )
    @swag_from("../swagger/violation/DELETE.yml")
    def delete(violation_id):
        """ Flag a violation as a false positive """
        repository = ViolationRepository()
        violation = repository.delete(violation_id = violation_id)
        return jsonify({violation.json})
