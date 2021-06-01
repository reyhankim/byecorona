"""
Define the User model
"""
from . import db
from .abc import BaseModel, MetaBaseModel


class Cctv(db.Model, BaseModel, metaclass=MetaBaseModel):
    """ The User model """

    __tablename__ = "cctv"

    cctv_id = db.Column(db.Integer, primary_key=True)
    cctv_name = db.Column(db.String(300), nullable=False)
    cctv_longitude = db.Column(db.Integer, nullable=False)
    cctv_latitude = db.Column(db.Integer, nullable=False)

    def __init__(self, cctv_id, cctv_name, cctv_longitude, cctv_latitude):
        """ Create a new User """
        self.cctv_id = cctv_id
        self.cctv_name = cctv_name
        self.cctv_longitude = cctv_longitude
        self.cctv_latitude = cctv_latitude
