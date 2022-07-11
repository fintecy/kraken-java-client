package org.fintecy.md.kraken.model.dto;

import org.fintecy.md.kraken.model.Asset;

import java.util.List;

public class AssetsResponse extends DataResponse<List<Asset>> {
    public AssetsResponse(List<Asset> value, List<String> errors) {
        super(value, errors);
    }
}
