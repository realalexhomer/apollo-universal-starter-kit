package util

import models.counter.Counter

import scala.concurrent.ExecutionContext

import scala.concurrent.Future
import models.counter.Counter.GraphQL.source

trait PublisherService {

  def withResultPublishing(f: => Future[Counter])(implicit executionContext: ExecutionContext): Future[Counter] = {
    f.map {
      counter =>
        source.onNext(counter)
        counter
    }
  }
}