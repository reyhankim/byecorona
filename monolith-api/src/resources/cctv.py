"""
Define the REST verbs relative to the users
"""

from flasgger import swag_from
from flask.json import jsonify
from flask_restful import Resource
from flask_restful.reqparse import Argument

from repositories import CctvRepository
from util import parse_params


class CctvResource(Resource):
    """ Verbs relative to the cctv """

    @staticmethod
    @parse_params(
        Argument("cctv_id", location="args", required=True, help="The unique ID of CCTV")
    )
    @swag_from("../swagger/cctv/GET.yml")
    def get(cctv_id):
        """ Return a cctv details based on its id """
        cctv = CctvRepository.get(cctv_id = cctv_id)
        return jsonify({cctv.json})