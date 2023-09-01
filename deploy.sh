#!/bin/bash

export HYPERFIDDLE_ELECTRIC_APP_VERSION=`git describe --tags --long --always --dirty`

echo $HYPERFIDDLE_ELECTRIC_APP_VERSION

NO_COLOR=1 fly deploy --build-arg VERSION="$HYPERFIDDLE_ELECTRIC_APP_VERSION"
