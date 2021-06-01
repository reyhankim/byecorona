"""
Define the User model
"""
from . import db
from .abc import BaseModel, MetaBaseModel
from geoalchemy2 import Geometry


class Cluster(db.Model, BaseModel, metaclass=MetaBaseModel):
    """ The User model """

    __tablename__ = "cluster"

    cluster_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    cluster_name = db.Column(db.String(300), nullable=False)
    cluster_latitude = db.Column(db.Float, nullable=False)
    cluster_longitude = db.Column(db.Float, nullable=False)
    geo = db.Column(Geometry(geometry_type = "POINT"))

    def __init__(self, cluster_id, cluster_name, cluster_longitude, cluster_latitude, geo):
        """ Create a new CCTV Cluster """
        self.cluster_id = cluster_id
        self.cluster_name = cluster_name
        self.cluster_longitude = cluster_longitude
        self.cluster_latitude = cluster_latitude
        self.geo = geo

    def __repr__(self):
        return "<Cluster {name} ({lat}, {lon})>".format(
            name = self.cluster_name, 
            lat = self.cluster_latitude,
            long = self.cluster_longitude
        )