package com.prisma.api.connector

import com.prisma.gc_values.{CuidGCValue, GCValue, IdGCValue}
import com.prisma.shared.models._

import scala.concurrent.Future

trait DataResolver {
  def project: Project

  def getNodeByGlobalId(globalId: CuidGCValue): Future[Option[PrismaNode]]

  def getNodeByWhere(where: NodeSelector): Future[Option[PrismaNode]]

  def getNodes(model: Model, args: Option[QueryArguments] = None): Future[ResolverResult[PrismaNode]]

  def getNodesByValuesForField(model: Model, field: ScalarField, values: Vector[GCValue]): Future[Vector[PrismaNode]]

  def getRelatedNodes(
      fromField: RelationField,
      fromNodeIds: Vector[IdGCValue],
      args: Option[QueryArguments]
  ): Future[Vector[ResolverResult[PrismaNodeWithParent]]]

  def getScalarListValues(model: Model, listField: ScalarField, args: Option[QueryArguments] = None): Future[ResolverResult[ScalarListValues]]

  def getScalarListValuesByNodeIds(model: Model, listField: ScalarField, nodeIds: Vector[IdGCValue]): Future[Vector[ScalarListValues]]

  def getRelationNodes(relationTableName: String, args: Option[QueryArguments] = None): Future[ResolverResult[RelationNode]]

  def countByTable(table: String, whereFilter: Option[Filter] = None): Future[Int]

  def countByModel(model: Model, whereFilter: Option[Filter] = None): Future[Int] = countByTable(model.name, whereFilter)
}
