""" Defines the Cctv repository """

from models import Cctv


class CctvRepository:
    """ The repository for the cctv model """

    @staticmethod
    def get(cctv_id):
        """ Query a cctv by its unique id """
        return Cctv.query.filter_by(cctv_id = cctv_id).one()

    @staticmethod
    def getAllCctvByClusterId(cctv_cluster_id):
        """ Query a cctv by its cluster id """
        return Cctv.query.filter_by(cctv_cluster_id = cctv_cluster_id).all()