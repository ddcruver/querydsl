package com.mysema.query.scala.sql;
import com.mysema.query.scala.BeanPath
import com.mysema.query.sql._
import com.mysema.query.types._
import com.mysema.query.types.PathMetadataFactory._
import java.util.ArrayList;
import scala.reflect.BeanProperty

/**
 * Implementation of RelationsPathImpl for Scala
 * 
 * @author tiwe
 *
 */
class RelationalPathImpl[T](md: PathMetadata[_])(implicit val mf: Manifest[T]) 
  extends BeanPath[T](mf.erasure.asInstanceOf[Class[T]], md) with RelationalPath[T] {
    
  private var primaryKey: PrimaryKey[T] = _
  
  @BeanProperty
  val columns: java.util.List[Path[_]] = new ArrayList[Path[_]]

  @BeanProperty
  val foreignKeys: java.util.List[ForeignKey[_]] = new ArrayList[ForeignKey[_]]
  
  @BeanProperty
  val inverseForeignKeys: java.util.List[ForeignKey[_]] = new ArrayList[ForeignKey[_]]
  
  def this(variable: String)(implicit mf: Manifest[T]) = this(forVariable(variable))(mf)
  
  override def add[P <: Path[_]](p: P): P = { columns.add(p); p }
  
  def all: Array[Path[_]] = columns.toArray[Path[_]](new Array[Path[_]](columns.size))
  
  def createPrimaryKey(cols: Path[_]*): PrimaryKey[T] = {
    primaryKey = new PrimaryKey[T](this, cols:_*); primaryKey
  }
  
  def createForeignKey[F](local: Path[_], foreign: String) = {
    val foreignKey = new ForeignKey[F](this, local, foreign)
    foreignKeys.add(foreignKey); foreignKey
  }
  
  def createInvForeignKey[F](local: Path[_], foreign: String) = {
    val foreignKey = new ForeignKey[F](this, local, foreign)
    inverseForeignKeys.add(foreignKey); foreignKey
  }
  
  def getPrimaryKey = primaryKey
  
  def getSchemaName = getType.getAnnotation(classOf[Schema]).value
  
  def getTableName = getType.getAnnotation(classOf[Table]).value
  
}