package engine

type ParseRequest struct {
	Url       string
	ParseFunc func([]byte) ParseResult
}

type ParseResult struct {
	Requests []ParseRequest
	Items    []interface{}
}

func NilParser([]byte) ParseResult {
	return ParseResult{}
}
