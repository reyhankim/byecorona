"""
Define the Health Violation model
"""
from . import db
from .abc import BaseModel, MetaBaseModel
from . import Cctv


class Violation(db.Model, BaseModel, metaclass=MetaBaseModel):
    """ The Violation model """

    __tablename__ = "violation"

    violation_id = db.Column(db.Integer, primary_key=True)
    violation_type = db.Column(db.String(64), nullable=False)
    violation_timestamp = db.Column(db.BigInteger, nullable=False)
    cctv_id = db.Column(db.Integer, db.ForeignKey('cctv.cctv_id'), nullable=False)
    thumbnail_path = db.Column(db.String(300), nullable=False)
    is_false_positive = db.Column(db.Boolean, nullable=False)
    is_action_taken = db.Column(db.Boolean, nullable=False)
    action_type = db.Column(db.String(64))
    action_timestamp = db.Column(db.BigInteger)
    action_remark = db.Column(db.String(300))
    remark_timestamp = db.column(db.BigInteger)
    action_by = db.column(db.String(64))


    def __init__(self, violation_type, violation_timestamp, 
                        cctv_id, thumbnail_path, is_false_positive = False, 
                        is_action_taken = False, action_type = None, 
                        action_timestamp = None, action_remark = None,
                        remark_time = None, action_by = None):
        """ Create a new Violation """
        self.violation_type = violation_type
        self.violation_timestamp = violation_timestamp
        self.cctv_id = cctv_id
        self.thumbnail_path = thumbnail_path
        self.is_false_positive = is_false_positive
        self.is_action_taken = is_action_taken
        self.action_type = action_type
        self.action_timestamp = action_timestamp
        self.action_remark = action_remark
        self.remark_timestamp = remark_time
        self.action_by = action_by