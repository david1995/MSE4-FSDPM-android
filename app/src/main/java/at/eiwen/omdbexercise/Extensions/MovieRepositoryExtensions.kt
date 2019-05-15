package at.eiwen.omdbexercise.Extensions

class MovieRepositoryExtensions
{
    companion object
    {
        fun GetQueryTerm(term : String) : String
        {
            if (term.isEmpty())
            {
                return term
            }

            try
            {
                val num = term.toInt()
                return num.toString()
            }
            catch (e : NumberFormatException)
            {
                return String.format("%%s%", term)
            }
        }
    }
}
