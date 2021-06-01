from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

from .user import User
from .cctv import Cctv
from .cluster import Cluster
from .violation import Violation