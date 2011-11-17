package test;

import com.mysema.query.types._;
import com.mysema.query.scala._;

import com.mysema.query.types.PathMetadataFactory._;
import com.mysema.query.sql.Table;
import com.mysema.query.sql.Schema;

import com.mysema.query.scala.sql.RelationalPathImpl;

import com.mysema.query.sql._;

object QEmployee {
  def as(variable: String) = new QEmployee(variable)
  
  val employee = as("employee")
}

class QEmployee(md: PathMetadata[_]) extends RelationalPathImpl[Employee]( md) {
  def this(variable: String) = this(forVariable(variable))

  def this(parent: Path[_], variable: String) = this(forProperty(parent, variable))

  val firstname = createString("FIRSTNAME")

  val id = createNumber("ID", classOf[Integer])

  val lastname = createString("LASTNAME")

  val superiorId = createNumber("SUPERIOR_ID", classOf[Integer])

  val sysIdx55: PrimaryKey[Employee] = createPrimaryKey(id);

  val superiorFk: ForeignKey[Employee] = createForeignKey(superiorId, "ID");

  val _superiorFk: ForeignKey[Employee] = createInvForeignKey(id, "SUPERIOR_ID");

}

