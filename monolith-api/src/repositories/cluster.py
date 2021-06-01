""" Defines the User repository """

from models import Cluster
from sqlalchemy import func


class ClusterRepository:
    """ The repository for the cluster model """

    @staticmethod
    def getClustersByLocation(reference_long, reference_lat, radius):
        """ Query clusters by its location represented in GoogleMaps longitude and latitude """
        reference_geo = 'POINT({} {})'.format(reference_long, reference_lat)
        return Cluster.query.filter_by(func.ST_Distance_Sphere(Cluster.geo, reference_geo) < radius).all()

    @staticmethod
    def getClusterById(cluster_id):
        """ Query a cluster by its cluster id """
        return Cluster.query.filter_by(cluster_id = cluster_id).one()