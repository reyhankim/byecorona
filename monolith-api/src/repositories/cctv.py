""" Defines the User repository """

from models import Cctv


class CctvRepository:
    """ The repository for the cctv model """

    @staticmethod
    def getCctv(cctv_id):
        """ Query a cctv by its cluster """
        return Cctv.query.filter_by(cctv_cluster_id = cctv_cluster_id).all()