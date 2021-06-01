""" Defines the Violation repository """

from models import Violation
import time


class ViolationRepository:
    """ The repository for the Violation model """

    @staticmethod
    def getViolation(violation_id):
        """ Query a violation by its unique id """
        return Violation.query.filter_by(violation_id = violation_id).one()


    @staticmethod
    def getAllViolationByCctvId(cctv_id):
        """ Query a violation by its cctv id """
        return Violation.query.filter_by(cctv_id = cctv_id).all()


    def update(self, violation_id, officer_id = "Pengguna Penguji", action_type = None, action_remark = None):
        """ Update violation action details """
        violation = self.getViolation(violation_id)
        if action_type:
            violation.is_action_taken = True
            violation.action_type = action_type
            violation.action_time = time.time()

        if action_remark:
            violation.action_remark = action_remark
            violation.remark_time = time.time()

        violation.officer_id = officer_id

        return violation.save()


    @staticmethod
    def create(violation_type, cctv_id, thumbnail_path):
        """ Create a new violation """
        violation = Violation(violation_type = violation_type, violation_timestamp = time.time(),
                                cctv_id = cctv_id, thumbnail_path = thumbnail_path)

        return violation.save()


    def delete(self, violation_id):
        """ Flag a violation as a false positive """
        violation = self.getViolation(violation_id)
        violation.is_false_positive = True

        return violation.save()

    

